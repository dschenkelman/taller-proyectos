package socialtoilet.android.services.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.model.Comment;
import socialtoilet.android.model.IComment;
import socialtoilet.android.model.IRating;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.IToiletPicture;
import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.LoginUser;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.services.factories.mocks.ModelMockFactory;
import socialtoilet.android.services.get.IRetrieveNearToiletsService;
import socialtoilet.android.services.get.IRetrieveNearToiletsServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletCommentsService;
import socialtoilet.android.services.get.IRetrieveToiletCommentsServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletGaleryService;
import socialtoilet.android.services.get.IRetrieveToiletGaleryServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletRatingService;
import socialtoilet.android.services.get.IRetrieveToiletRatingServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletService;
import socialtoilet.android.services.get.IRetrieveToiletServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletTraitsService;
import socialtoilet.android.services.get.IRetrieveToiletTraitsServiceDelegate;
import socialtoilet.android.services.get.IRetrieveTraitsService;
import socialtoilet.android.services.get.IRetrieveTraitsServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletUserQualificationService;
import socialtoilet.android.services.get.IRetrieveToiletUserQualificationServiceDelegate;
import socialtoilet.android.services.get.RetrieveNearToiletsService;
import socialtoilet.android.services.get.RetrieveToiletCommentsService;
import socialtoilet.android.services.get.RetrieveToiletGaleryService;
import socialtoilet.android.services.get.RetrieveToiletRatingService;
import socialtoilet.android.services.get.RetrieveToiletService;
import socialtoilet.android.services.get.RetrieveToiletTraitsService;
import socialtoilet.android.services.get.RetrieveTraitsService;
import socialtoilet.android.services.get.RetrieveToiletUserQualificationService;
import socialtoilet.android.services.post.AddToiletCommentService;
import socialtoilet.android.services.post.AddToiletService;
import socialtoilet.android.services.post.AuthService;
import socialtoilet.android.services.post.IAddToiletCommentService;
import socialtoilet.android.services.post.IAddToiletCommentServiceDelegate;
import socialtoilet.android.services.post.IAddToiletService;
import socialtoilet.android.services.post.IAddToiletServiceDelegate;
import socialtoilet.android.services.post.IAuthService;
import socialtoilet.android.services.post.IAuthServiceDelegate;
import socialtoilet.android.services.post.IQualificateToiletService;
import socialtoilet.android.services.post.IQualificateToiletServiceDelegate;
import socialtoilet.android.services.post.QualificateToiletService;
import socialtoilet.android.services.put.EditQualificationToiletService;
import socialtoilet.android.services.put.EditToiletTraitsService;
import socialtoilet.android.services.put.IEditQualificationToiletService;
import socialtoilet.android.services.put.IEditQualificationToiletServiceDelegate;
import socialtoilet.android.services.put.IEditToiletTraitsService;
import socialtoilet.android.services.put.IEditToiletTraitsServiceDelegate;
import socialtoilet.android.utils.Settings;

public class ServicesFactory
{
	private ServicesFactory() {}
	
	public static IAddToiletService createAddToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode() )
		{
			return new IAddToiletService()
			{
				@Override
				public void addToilet(Toilet toilet, IAddToiletServiceDelegate delegate)
				{
					toilet.setID(UUID.randomUUID());
					ModelMockFactory.getIntance().addToilet(toilet);
					delegate.addToiletFinish(this, toilet);
				}
			};
		}
		return new AddToiletService();
	}
	
	public static IRetrieveNearToiletsService createRetrieveNearToiletsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveNearToiletsService()
			{
				@Override
				public void retrieveNearToilets(double latitude, double longitude,
						int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate)
				{
					Collection<IToilet> toilets = 
							ModelMockFactory.getIntance().getNearToilets();
					delegate.retrieveNearToiletsFinish(this, toilets);
				}
			};
		}
		return new RetrieveNearToiletsService();
	}
	
	public static IRetrieveToiletService createRetrieveToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletService()
			{
				@Override
				public void retrieveToilet(UUID toiletId,
						IRetrieveToiletServiceDelegate detailToiletActivity)
				{
					IToilet toilet = ModelMockFactory.getIntance().getToilet(toiletId);
					detailToiletActivity.retrieveToiletFinish(this, toilet);
				}
			};
		}
		return new RetrieveToiletService();
	}
	
	public static IQualificateToiletService createQualificateToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IQualificateToiletService()
			{
				@Override
				public void qualificateToiletService(IToilet toilet, int userCalification,
						IQualificateToiletServiceDelegate delegate)
				{
					toilet.setUserCalification(userCalification);
					delegate.qualificateToiletFinish(this);
				}
			};
		}
		return new QualificateToiletService();
	}

	public static IRetrieveToiletCommentsService createRetrieveToiletCommentsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletCommentsService()
			{

				@Override
				public void retrieveToiletComments(UUID toiletId,
						IRetrieveToiletCommentsServiceDelegate delegate)
				{
					Collection<IComment> comments = 
							ModelMockFactory.getIntance().getToiletComments(toiletId);
					delegate.retrieveToiletCommentsFinish(this, comments);
				}
			};
		}
		return new RetrieveToiletCommentsService();
	}

	public static IRetrieveToiletGaleryService createRetrieveToiletGaleryService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletGaleryService()
			{

				@Override
				public void retrieveToiletGalery(UUID toiletId,
						IRetrieveToiletGaleryServiceDelegate delegate)
				{
					Collection<IToiletPicture> pictures = new ArrayList<IToiletPicture>();
					// TODO mock some pictures in modelmock
					delegate.retrieveToiletGaleryServiceFinish(this, pictures);
				}
			};
		}
		return new RetrieveToiletGaleryService();
	}

	public static IAddToiletCommentService createAddToiletCommentService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IAddToiletCommentService()
			{
				@Override
				public void addToiletComment(
						IAddToiletCommentServiceDelegate delegate,
						String toiletId, Comment comment)
				{
					ModelMockFactory.getIntance().addToiletComment(toiletId, comment);
					delegate.addToiletCommentFinish(this, comment);
				}
			};
		}
		return new AddToiletCommentService();
	}

	public static IRetrieveToiletRatingService createRetrieveToiletRatingService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletRatingService()
			{
				@Override
				public void retrieveToiletRating(String toiletId,
						IRetrieveToiletRatingServiceDelegate delegate)
				{
					IToilet toilet = ModelMockFactory.getIntance().getToilet(UUID.fromString(toiletId));
					final float rating = toilet.getRanking();
					final int count = toilet.getUserCalificationsCount();
					
					delegate.retrieveToiletRatingServiceFinish(this, new IRating()
					{
						@Override
						public int getCalificationCount() { return count; }
						@Override
						public float getRating() { return rating; }
					});
				}
			};
		}
		return new RetrieveToiletRatingService();
	}

	public static IAuthService createAuthService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IAuthService()
			{
				@Override
				public void authUser(IAuthServiceDelegate delegate, LoginUser user)
				{
					if(ModelMockFactory.getIntance().authUser(user))
					{
						delegate.authServiceDelegateFinish(this, user);
					}
					else
					{
						delegate.authServiceDelegateFinishWithError(this, "No user");	
					}
				}
			};
		}
		return new AuthService();
	}

	public static IRetrieveToiletUserQualificationService
		createRetrieveToiletUserCalificationService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletUserQualificationService()
			{
				@Override
				public void retrieveToiletUserCalification(
						IRetrieveToiletUserQualificationServiceDelegate delegate,
						String toiletId)
				{
					if(ModelMockFactory.getIntance().userQualificateToilet(toiletId))
					{
						delegate.retrieveToiletUserQualificationServiceFinish(
								this, ModelMockFactory.getIntance().getUserQualification(toiletId));
					}
					else
					{
						delegate.retrieveToiletUserQualificationServiceFinishWithError(this, 402);
					}
				}
			};
		}
		return new RetrieveToiletUserQualificationService();
	}

	public static IEditQualificationToiletService createEditCalificationToiletService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IEditQualificationToiletService()
			{
				@Override
				public void editQualificationToiletService(IToilet toilet,
						int userQualification,
						IEditQualificationToiletServiceDelegate delegate)
				{
					delegate.editQualificationToiletFinish(this, userQualification);
				}
			};
		}
		return new EditQualificationToiletService();
	}

	public static IRetrieveTraitsService createRetrieveTraitsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveTraitsService()
			{
				@Override
				public void retrieveTraits(
						IRetrieveTraitsServiceDelegate delegate)
				{
					Collection<IToiletTrait> traits = new ArrayList<IToiletTrait>();
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 0; }
						@Override
						public String getDescription() { return "Dejan pasar sin consumir"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 1; }
						@Override
						public String getDescription() { return "Tiene agua"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 2; }
						@Override
						public String getDescription() { return "Tiene papel"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 3; }
						@Override
						public String getDescription() { return "Tiene jabon"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 4; }
						@Override
						public String getDescription() { return "Tiene espejo"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 5; }
						@Override
						public String getDescription() { return "Las puertas de las cabinas cierran"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 6; }
						@Override
						public String getDescription() { return "Tiene insumos femeninos a la venta"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 7; }
						@Override
						public String getDescription() { return "Tiene preservativos a la venta"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 8; }
						@Override
						public String getDescription() { return "Apto para discapacitados"; }
					});
					traits.add(new IToiletTrait()
					{
						private boolean value = false;
						@Override
						public boolean hasDescription() { return value; }
						@Override
						public void setHasDescription(boolean has) { value = has; }
						@Override
						public int getId() { return 9; }
						@Override
						public String getDescription() { return "Tiene cambiador de bebes"; }
					});
					delegate.retrieveTraitsServiceFinish(this, traits);
				}
			};
		}
		return new RetrieveTraitsService();
	}

	public static IEditToiletTraitsService createEditToiletTraitsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IEditToiletTraitsService()
			{
				@Override
				public void editToiletTraits(
						IEditToiletTraitsServiceDelegate delegate,
						Collection<IToiletTrait> traits, String toiletId)
				{
					ModelMockFactory.getIntance().editTraits(toiletId, traits);
					delegate.editToiletTraitsServiceFinish(this);
				}
			};
		}
		return new EditToiletTraitsService();
	}

	public static IRetrieveToiletTraitsService createRetrieveToiletTraitsService()
	{
		if(Settings.getInstance().isServicesDebugMode())
		{
			return new IRetrieveToiletTraitsService()
			{

				@Override
				public void retrieveToiletTraitService(
						IRetrieveToiletTraitsServiceDelegate delegate,
						IToilet toilet)
				{
					Collection<IToiletTrait> traits =
							ModelMockFactory.getIntance().getTraits(toilet.getID());
					delegate.retrieveToiletTraitsServiceFinish(this, traits);
				}
			};
		}
		return new RetrieveToiletTraitsService();
	}
}
