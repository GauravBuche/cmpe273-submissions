package application

import org.springframework.boot.SpringApplication
import java.util.HashMap
import resources.User
import resources.IdCard
import resources.WebLogin
import resources.BankAccount
//import HelloConfig.
/**
 * This object bootstraps Spring Boot web application.
 * Via Gradle: gradle bootRun
 *
 * @author saung
 * @since 1.0
 */

object DigitalWalletApplication {
	
	var userHashMap = new HashMap[Int, User]
        var idcardHashMap = new HashMap[Int, HashMap[Int, IdCard]]
	var webloginHashMap = new HashMap[Int, HashMap[Int, WebLogin]] 
	var baHashMap = new HashMap[Int, HashMap[Int, BankAccount]]

        System.out.println("Map created!")
	def main(args: Array[String]) {
	   SpringApplication.run(classOf[DigitalWallet])    	
	}
}
