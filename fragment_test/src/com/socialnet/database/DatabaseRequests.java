package com.socialnet.database;

public class DatabaseRequests {
	
	public static final String CheckUser="select user_id from authorisation where login=? and password=?"; 
	public static final String SelectOneUserAnketa="select name, surname from  anketa where anketa.user_id = ?";
	public static final String SelectAnketaInFriendslist="select user_id, surname, name, adress_folder, adress_image from anketa  where  user_id in (select friend_id from friends where user_id= ? )";

	
	//С‚Р°Р±Р»РёС†Р° users
	public static final String InsertUser="call insert_user (?, ?)";   
	public static final String UpdateUser="update users set name=?, surname=? where id= ? "; 
	//public static final String SelectOneUser="select name, surname from  users where id= ? ";   
	public static final String SelectAllUsers="select name, surname from  users";   
	public static final String DeleteUser="delete from users where id= ? ";   
     
	
	//region
	public static final String SelectFromRegion="select id, name from region";   
	public static final String SelectFromCity="select id, region_id, name from city where region_id=?";
	
	//authorisation
	public static final String InsertIntoAuthorisation="CALL insert_in_authorisation(?, ?, ?);";
	
	public static final String InsertIntoAnketa="insert into anketa (user_id, name, surname, mobile_phone, id_region, id_city) values (?, ?, ?, ?, ?, ?)";
			//+ //"mobile_phone, id_region, id_city) values (?, ?, ?, ?, ?, ?) "; 
	
	  
	//dialogs  
	public static final String Dialogs_one_user = "SELECT  U.user_id, D.d_id,  A.name, A.surname,  D.time, A.adress_folder, A.adress_image "+
" FROM authorisation U, dialogs D, anketa A  WHERE CASE ? "+
" WHEN D.user_one THEN D.user_two = U.user_id WHEN D.user_two  THEN D.user_one= U.user_id END "+
 " And A.user_id = U.user_id "+
" ORDER BY D.d_id DESC";
	
	public static final String InsertIntoDialogs="insert into dialogs (user_one, user_two, time) values ( ? , ? , ? );";
	
	//проверка на существование диалога - в columns указать user_one-user_two-user_one-user_two (так как 4 значения)
	
	public static final String IsDialog = "SELECT d_id FROM dialogs  WHERE (user_one= ?  AND user_two= ? )"+
                                           "OR (user_one= ?  AND user_two= ? )";

	
	//messages
	
	public static final String Messages_one_users = "select M.ms_id, m.time, m.Reply, U.user_id, A.name, A.surname "+
		"	from authorisation U, messages M, anketa A where M.user_id_sender=U.user_id "+
		"	and M.d_id= ?  and A.user_id=U.user_id ; "; 
	
	public static final String LastMessage = " SELECT ms_id, reply,  time  FROM messages WHERE d_id= ? ORDER BY ms_id DESC LIMIT 1";
	

	//register device in gcm service
	public static final String Reg_device_Id = "insert into registr_gsm_id (user_id, reg_id) values ( ? ,  ? );";
	
public static final String InsertIntoMessages="insert into messages (reply, user_id_sender, time, d_id) values ( ? ,   ? ,  ? , ? );";
	
	
	
			
			
			
}
