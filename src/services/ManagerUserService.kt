package services

import models.ManagerUser

import models.DevUser
import models.Project

class ManagerUserService : AbstractService() {

	fun createUser(): ManagerUser {

		var user: ManagerUser = ManagerUser()
		
		println("Type your name: ")
		var name: String = readLine()!!.toString()
		user.name = name;

		println("Do you have any projects? Yes (1) No (2)")
		var op: Int = readLine()!!.toInt()

		if (op == 1) {

			var operation: Int = 1;


			while (operation == 1) {

				var project: Project = Project()

				println("Type the name of the project: ")
				project.name = readLine()!!.toString()

				println("Type the language of the project: ")
				project.language = readLine()!!.toString()

				project.id = user.projects.size + 1;
				user.projects.add(project);

				println("Do you still have projects? Yes (1) No (2)")
				operation = readLine()!!.toInt()


			}


		}


		println("Do you have any developers that work with you? Yes (1) No (2) ")
		var operation: Int = readLine()!!.toInt()


		if (operation == 1) {
			var option: Int = 1;

			while (option == 1) {
				
				var devService: DevUserService = DevUserService()
				var devUser: DevUser = devService.createUser()

				devUser.id = user.devs.size + 1;
				user.devs.add(devUser);

				println("Do you still have devs? Yes (1) No (2)")
				option = readLine()!!.toInt()
			}

		} 

		return user;
	}
	
	fun findDevById(manager: ManagerUser, id: Int): DevUser {
		var dev: DevUser = DevUser()
		try {
			return manager.devs.single { it.id == id }
		} catch (e: NoSuchElementException) {
			println("This ID does not exist")
			return dev;
		}
	}


	fun changeOwnCredits(manager: ManagerUser) {

		println("Do you want to add (1) or remove (2) your credits?")
		var op: Int = readLine()!!.toInt()

		when (op) {
			1 -> addOwnCredits(manager)
			2 -> removeOwnCredits(manager)
		}


	}

	fun changeDevsCredits(manager: ManagerUser) {


		println("Type the dev's ID: ")
		var id: Int = readLine()!!.toInt()
		var dev: DevUser = findDevById(manager, id)

		println("Do you want to add (1) or remove (2) their credits?")
		var op: Int = readLine()!!.toInt()

		when (op) {
			1 -> addDevsCredits(dev)
			2 -> removeDevsCredits(dev)
		}


	}


	fun addOwnCredits(manager: ManagerUser) {
		println("How much do you want to add?")
		var credits: Int = readLine()!!.toInt()
		manager.credits += credits;

	}

	fun removeOwnCredits(manager: ManagerUser) {
		println("How much do you want to remove?")
		var credits: Int = readLine()!!.toInt()
		manager.credits -= credits;

	}

	fun addDevsCredits(dev: DevUser) {
		println("How much do you want to add?")
		var credits: Int = readLine()!!.toInt()
		dev.credits += credits;

	}

	fun removeDevsCredits(dev: DevUser) {
		println("How much do you want to remove?")
		var credits: Int = readLine()!!.toInt()
		dev.credits -= credits;

	}

	fun createDev(manager: ManagerUser) {
		var devService: DevUserService = DevUserService()
		var dev: DevUser

		dev = devService.createUser()
		dev.id = returnTheIdOfDevs(manager)
		manager.devs.add(dev)
	}

	fun deleteDev(manager: ManagerUser) {
		println("Type the ID of the dev that you want to delete: ")
		var devId: Int = readLine()!!.toInt()
		manager.devs.remove(findDevById(manager, devId))
	}

	fun returnTheIdOfDevs(user: ManagerUser): Int {
		var id: Int = 1;

		for (i in 1..user.devs.size) {

			try {
				user.devs.single { it.id == i }
				id = i + 1;
			} catch (e: NoSuchElementException) {
				return i;
			}
		}

		return id;
	}

}