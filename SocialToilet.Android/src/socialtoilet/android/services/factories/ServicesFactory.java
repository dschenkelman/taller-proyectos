package socialtoilet.android.services.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import android.util.Log;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.model.Comment;
import socialtoilet.android.model.IComment;
import socialtoilet.android.model.IRating;
import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.IToiletPicture;
import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.LoginUser;
import socialtoilet.android.model.Toilet;
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
import socialtoilet.android.services.get.IRetrieveTraitsService;
import socialtoilet.android.services.get.IRetrieveTraitsServiceDelegate;
import socialtoilet.android.services.get.IRetrieveToiletUserQualificationService;
import socialtoilet.android.services.get.IRetrieveToiletUserQualificationServiceDelegate;
import socialtoilet.android.services.get.RetrieveNearToiletsService;
import socialtoilet.android.services.get.RetrieveToiletCommentsService;
import socialtoilet.android.services.get.RetrieveToiletGaleryService;
import socialtoilet.android.services.get.RetrieveToiletRatingService;
import socialtoilet.android.services.get.RetrieveToiletService;
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
					Collection<IToilet> toilets = new ArrayList<IToilet>();
					toilets.add(new IToilet()
						{
							public String getMapTitle() { return "La Roma"; }
							public String getMapSnippet() { return "También venden pizza.";}
							public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.001; }
							public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.002; }
							public float getRanking() { return 2.5f; }
							public UUID getID() { return UUID.randomUUID(); }
							public String getDescription() { return "Es un buen baño"; }
							public String getAddress() { return "Av. Yrigoyen 1565, El Talar"; }
							public int getUserCalification() { return 0; }
							public int getUserCalificationsCount() { return 25; }
							public void setUserCalification(int calification) { }
							public void revertUserCalification() { }
							public void setRating(IRating rating) { }
							public boolean canBeUsedWithoutConsumption() { return false; }
							public boolean hasWater() { return false; }
							public boolean hasToiletPaper() { return false; }
							public boolean hasSoap() { return false; }
							public boolean hasMirror() { return false; }
							public boolean doToiletDoorCloses() { return false; }
							public boolean hasGotLadiesItemsOnSale() { return false; }
							public boolean hasGotCondomsOnSale() { return false; }
							public boolean isAptForHandicapped() { return false; }
							public boolean hasBabyRoom() { return false; }
							public void userCalificationRetrieved( int calification) { }
						});
					toilets.add(new IToilet()
						{
							public String getMapTitle() { return "Eso"; }
							public String getMapSnippet() { return "Siempre cerrado.";}
							public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.003; }
							public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.005; }
							public float getRanking() { return 2.5f; }
							public UUID getID() { return UUID.randomUUID(); }
							public String getDescription() { return "Esta re cuidado"; }
							public String getAddress() { return "Av. Yrigoyen 1355, El Talar"; }
							public int getUserCalification() { return 2; }
							public int getUserCalificationsCount() { return 31; }
							public void setUserCalification(int calification) { }
							public void revertUserCalification() { }
							public void setRating(IRating rating) { }
							public boolean canBeUsedWithoutConsumption() { return false; }
							public boolean hasWater() { return false; }
							public boolean hasToiletPaper() { return false; }
							public boolean hasSoap() { return false; }
							public boolean hasMirror() { return false; }
							public boolean doToiletDoorCloses() { return false; }
							public boolean hasGotLadiesItemsOnSale() { return false; }
							public boolean hasGotCondomsOnSale() { return false; }
							public boolean isAptForHandicapped() { return false; }
							public boolean hasBabyRoom() { return false; }
							public void userCalificationRetrieved( int calification) { }
						});
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
					final UUID id = toiletId;
					detailToiletActivity.retrieveToiletFinish(this, new IToilet()
					{
						private int userCalification = 0;
						private float ranking = 1.5f;
						private int userCalificationsCount = 2;
						public String getMapTitle() { return "La Roma"; }
						public String getMapSnippet() { return "También venden pizza.";}
						public double getLongitude() { return -34.465849; }
						public double getLatitude() { return -58.645337; }
						public float getRanking() { return ranking; }
						public UUID getID() { return id; }
						public String getDescription() { return "Es un buen baño"; }
						public String getAddress() { return "Av. Yrigoyen 1565, El Talar"; }
						public int getUserCalification() { return userCalification; }
						public int getUserCalificationsCount() { return userCalificationsCount; }
						public void setUserCalification(int calification)
						{
							Log.d("Social Toilet", "Ranking viejo: " + ranking);
							if(0 == userCalification)
							{
								ranking = (ranking * userCalificationsCount + calification) / (userCalificationsCount + 1);
								userCalificationsCount++;
							}
							else
							{
								ranking = (ranking * userCalificationsCount - userCalification) / (float)(userCalificationsCount - 1);
								ranking = (ranking * (userCalificationsCount - 1) + calification) / (float)userCalificationsCount;
							}
							userCalification = calification;
							Log.d("Social Toilet", "Ranking Nuevo: " + ranking);
						}
						public void revertUserCalification()
						{
							if(0 != userCalification)
							{
								ranking = (ranking * userCalificationsCount - userCalification) / (float)(userCalificationsCount - 1);
								userCalification = 0;
								userCalificationsCount--;
							}
						}
						public void setRating(IRating rating) { }
						public boolean canBeUsedWithoutConsumption() { return false; }
						public boolean hasWater() { return true; }
						public boolean hasToiletPaper() { return true; }
						public boolean hasSoap() { return false; }
						public boolean hasMirror() { return true; }
						public boolean doToiletDoorCloses() { return false; }
						public boolean hasGotLadiesItemsOnSale() { return false; }
						public boolean hasGotCondomsOnSale() { return true; }
						public boolean isAptForHandicapped() { return true; }
						public boolean hasBabyRoom() { return false; }
						public void userCalificationRetrieved( int calification) { }
					});
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
					Collection<IComment> comments = new ArrayList<IComment>();
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "damian"; }
						@Override
						public String getContent() { 
							return "Totalmente recomendado."; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "15:56hs 2013/07/13"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "sebas"; }
						@Override
						public String getContent() { 
							return "Cuando llegué, estaba cerrado. Pero me lo abrieron y después me tome un café muy bueno."; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(
							    Arrays.asList("damian", "gus", "matias")); }
						@Override
						public String getDate() { return "11:11hs 2013/07/18"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "mservetto"; }
						@Override
						public String getContent() { 
							return "Ya no están limpiando este baño. Están las tablas sucias"; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "25:25hs 2013/07/22"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "damian"; }
						@Override
						public String getContent() { 
							return "Que raro, cuando fui yo estaba 10 puntos."; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "15:25hs 2013/07/22"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "gus"; }
						@Override
						public String getContent() { 
							return "Lo confirmo."; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "5:05hs 2013/07/24"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "ricardo"; }
						@Override
						public String getContent() { 
							return "Disculpen por las molestias. ya lo limpiamos y lo pusimos  punto. Vengan y de paso coman nuestras tortas, las más ricas de Buenos Aires!"; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "88:99hs 2013/07/25"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "mservetto"; }
						@Override
						public String getContent() { 
							return "Las mejores!!"; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "24:00hs 2013/07/25"; }
					});
					comments.add(new IComment()
					{
						@Override
						public UUID getUserId() { return UUID.randomUUID(); }
						@Override
						public String getUser() { return "sebas"; }
						@Override
						public String getContent() { 
							return "Genio. Amo esta aplicación"; }
						@Override
						public Collection<String> getLikeUsers() { return new ArrayList<String>(); }
						@Override
						public String getDate() { return "15:99hs 2013/07/26"; }
					});
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
					// TODO mock some pictures
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
				public void retrieveToiletRating(String string,
						IRetrieveToiletRatingServiceDelegate delegate)
				{
					delegate.retrieveToiletRatingServiceFinish(this, new IRating()
					{
						@Override
						public int getCalificationCount() { return 4; }
						@Override
						public float getRating() { return 1.2f; }
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
					user.setUserId(UUID.randomUUID());
					delegate.authServiceDelegateFinish(this, user);
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
						IRetrieveToiletUserQualificationServiceDelegate delegate, String toiletId) {
					delegate.retrieveToiletUserQualificationServiceFinishWithError(this, 402);
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
						IEditQualificationToiletServiceDelegate delegate) {
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
				public void retrieveToiletTraits(
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
					delegate.retrieveToiletTraitsServiceFinish(this, traits);
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
					delegate.editToiletTraitsServiceFinish(this);
				}
			};
		}
		return new EditToiletTraitsService();
	}
}
