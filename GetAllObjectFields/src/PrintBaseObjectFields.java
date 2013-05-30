import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lancill.objectfields.pojo.User;

public class PrintBaseObjectFields {

	public static void main(String[] args) {

		User user = getUser();
		printBaseUserField(user, "user");
	}

	
	private static void printBaseUserField(Object object, String aFieldName) {

		String parentFieldName = aFieldName;

		try {
			//recupero tutti i campi della classe del mio ogetto
			Field[] classFields = object.getClass().getDeclaredFields();
	
			for (Field field : classFields) {
				//imposto il campo accessibile altrimenti JAva solleva una eccezione
				field.setAccessible(true);
	
				String fieldName = field.getName();
				Object value;
				try {
					//recupero il valore del campo per l'istanzia della classe
					value = field.get(object);
					System.out.println("FIELD UTENTE: Nome attributo: "
							+ parentFieldName + "." + fieldName + ", Valore attributo: "
							+ value);
				} catch (Exception e) {
					System.out.println("Impossobile recuperare una proprieta'");
					e.printStackTrace();
				}
	
			}
		} catch (Exception e) {
			System.out.println("Errore recupero field di "+aFieldName);
			e.printStackTrace();
		}

	}
	
	private static User getUser() {
		User user = new User();
		user.setName("Peter");
		user.setName("Pan");
		user.setAge("12");
		user.setMail("peterpan@isolachenonce.net");
		user.setUserid("pp");
		
		List<String> contacts = new ArrayList<>();
		contacts.add("555010101");
		contacts.add("333000000");
		
		user.setContacts(contacts);
		
		List<User> friends = new ArrayList<User>();
		friends.add(new User("Campanellino"));
		friends.add(new User("Mary"));
		
		user.setFriends(friends);
		
		return user;
	}
	
}
