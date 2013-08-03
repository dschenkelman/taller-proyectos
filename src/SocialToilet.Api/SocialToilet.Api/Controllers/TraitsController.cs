namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Linq;
    using System.Threading.Tasks;

    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public class TraitsController : BaseController
    {
        public async Task<IEnumerable<Trait>> Get()
        {
            return await this.db.Traits.ToListAsync();
        }

        public async Task<IEnumerable<ToiletTraitViewModel>> Get(Guid toiletId)
        {
            var toiletTraits = await this.db.Toilets
                .Where(t => t.Id == toiletId)
                .Select(t => t.Traits).FirstOrDefaultAsync();

            var traits = await this.db.Traits.ToListAsync();

            return traits
                .Select(trait => new ToiletTraitViewModel
                                     {
                                         TraitId = trait.Id,
                                         Description = trait.Description, 
                                         IsActive = toiletTraits.Any(t => t.Id == trait.Id)
                                     });
        }

        public async Task Put(Guid toiletId, IEnumerable<TraitEditionViewModel> traitsEditionViewModels)
        {
            var toiletTraits = await this.db.Toilets.Where(t => t.Id == toiletId)
                                         .Select(t => new { Toilet = t, t.Traits })
                                         .FirstOrDefaultAsync();

            var traits = await this.db.Traits.ToListAsync();

            foreach (var traitEditionViewModel in traitsEditionViewModels)
            {
                var existingTrait = toiletTraits.Traits.FirstOrDefault(t => t.Id == traitEditionViewModel.TraitId);

                if (traitEditionViewModel.IsActive)
                {
                    if (existingTrait == null)
                    {
                        toiletTraits.Toilet.Traits.Add(traits.First(t => t.Id == traitEditionViewModel.TraitId));
                    }  
                }
                else
                {
                    if (existingTrait != null)
                    {
                        toiletTraits.Toilet.Traits.Remove(existingTrait);
                    }
                }
            }

            await this.db.SaveChangesAsync();
        }
    }
}