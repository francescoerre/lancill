import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import lancill.objectfields.pojo.User;

public class PrintAllObjectFields {

	public static void main(String[] args) {

		User user = getUser();
		printBaseUserField(user, "user");
		System.out.println("\n***********************************************\n");
		printAllUserField(user, "user");
	}

	
	private static void printBaseUserField(Object object, String aFieldName) {

		String parentFieldName = aFieldName;

		try {
			Field[] classFields = object.getClass().getDeclaredFields();
	
			for (Field field : classFields) {
				field.setAccessible(true);
	
				String fieldName = field.getName();
				Object value;
				try {
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
	
	private static void printAllUserField(Object object, String aFieldName) {

		
		String parentFieldName = aFieldName;

		try{
			
		
			Field[] classFields = object.getClass().getDeclaredFields();
	
			String PACKAGE = "lancill.objectfields.pojo";
			
			Field [] superClassFields;
			Field [] allClassFields = ArrayUtils.addAll(classFields);
			
			//Recupero gli attributi della classe Person
			Class superClass = object.getClass().getSuperclass();
			if(superClass.getCanonicalName().startsWith(PACKAGE)){
				superClassFields = superClass.getDeclaredFields();
				
				allClassFields = ArrayUtils.addAll(superClassFields, classFields);
			}
			
			
			for (Field field : allClassFields) {
				field.setAccessible(true);
	
				String fieldName = field.getName();
				Object value;
				try {
					String canonicalName =field.getType().getCanonicalName(); 
					if(!canonicalName.startsWith("java.util.List") && !canonicalName.startsWith(PACKAGE)){
	
						value = field.get(object);
						System.out.println("FIELD UTENTE: Nome attributo: "
								+ parentFieldName + "." + fieldName + ", Valore attributo: "
								+ value);
					}else{
						Object complexObj = field.get(object);
						
						if(complexObj!=null){
							if(complexObj instanceof List){//GESTIONE LISTE
								System.out.println("FIELD UTENTE: START Attributo complesso:"+parentFieldName+"."+fieldName);
								List<Object> list = (List<Object>)complexObj;
								int count=1;
								for(Object objInList : list){
									String objCanonicalName =objInList.getClass().getCanonicalName(); 
									if(!objCanonicalName.startsWith(PACKAGE)){//se il tipo dell'oggetto non è complesso
										System.out.println("FIELD UTENTE: Nome attributo: "
												+ parentFieldName + "." + fieldName +".[elemento-"+count+"], Valore attributo: "
												+ objInList);
									}else{
										printAllUserField(objInList, parentFieldName+"."+fieldName+".[elemento-"+count+"]");
									}
									count++;
								}
								System.out.println("FIELD UTENTE: END Attributo complesso:"+fieldName);
							}else{
								System.out.println("FIELD UTENTE: START Attributo complesso:"+parentFieldName+"."+fieldName); 
								printAllUserField(complexObj, parentFieldName+"."+fieldName);
								System.out.println("FIELD UTENTE: END Attributo complesso:"+fieldName);
							}
						}
					}
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
