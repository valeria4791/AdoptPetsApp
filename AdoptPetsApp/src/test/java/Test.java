
import java.util.List;

import group.entities.Pet;
import group.entities.User;
import group.models.LikeModel;
import group.models.PetModel;
import group.models.UserModel;
import group.utilities.Category;
import group.utilities.ConvertPhoto;
import group.utilities.Gender;
import group.utilities.PetSize;

public class Test {

	public static void main(String[] args) {

		// 1. Create User
		UserModel userModel = new UserModel();
		try {
			userModel.createNewUser("valeria", "Aa123456", "Valeria", "Yermaev", "valeria@gmail.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 2. Create Per + Owner x 4
		PetModel petModel = new PetModel();
		try {

			// URL photo
			String urlPhoto = "C:\\Users\\Valeria\\OneDrive\\Pictures\\Study\\profile.jpg";

			byte[] bytePhoto = null;

			try {
				bytePhoto = ConvertPhoto.convertPhotoToDB(urlPhoto);
			} catch (Exception e) {
			}

			// Get created user
			User user = userModel.findUser("valeria", "Aa123456");

			long number = Long.valueOf("0544458996");
			petModel.createNewPet(user.getUserId(), Category.DOG, "Lucky", 5, PetSize.LARGE, Gender.MALE,
					"הכלב הכי חמוד שיש", "הכלב הכי חמוד שיש.לא נושך ולא נובח", bytePhoto, "Valeria", "Yermaev", number,
					"תל אביב", "רודשילד", 16);
			petModel.createNewPet(user.getUserId(), Category.DOG, "Moana", 1, PetSize.SMALL, Gender.FEMALE,
					"הכלבה הכי חמודה שיש", "הכלבה הכי חמודה שיש.לא נושכת ולא נובחת", bytePhoto, "Valeria", "Yermaev",
					number, "תל אביב", "רודשילד", 16);
			petModel.createNewPet(user.getUserId(), Category.CAT, "Misty", 3, PetSize.XLARGE, Gender.MALE,
					"החתול הכי חמוד שיש", "החתול הכי חמוד שיש.לא נושך ולא נובח", null, "Valeria", "Yermaev", number,
					"תל אביב", "רודשילד", 16);
			petModel.createNewPet(user.getUserId(), Category.CAT, "Chloe", 7, PetSize.SMALL, Gender.MALE,
					"החתול הכי חמוד שיש", "החתול הכי חמוד שיש.לא נושך ולא נובח", null, "Valeria", "Yermaev", number,
					"תל אביב", "רודשילד", 16);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 3. Create Likes of user x 2
		LikeModel likeModel = new LikeModel();
		try {

			// Get created user
			User user = userModel.findUser("valeria", "Aa123456");

			// Get created pets - Dogs
			List<Pet> pets = petModel.getPetsByCriteria(Category.DOG, 0, null, null);

			for (Pet currPet : pets) {
				likeModel.createNewLike(user.getUserId(), currPet.getPetId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 4. Get user likes
		try {

			// Get created user
			User user = userModel.findUser("valeria", "Aa123456");

			// Get created pets - Dogs
			List<Pet> pets = likeModel.getAllLikes(user.getUserId());

			for (Pet currPet : pets) {
				System.out.println(currPet.getPetName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
