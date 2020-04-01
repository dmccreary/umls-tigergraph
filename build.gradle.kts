import com.optum.giraffle.tasks.GsqlTask

plugins {
    id("com.optum.giraffle") version "1.3.3"
    id("net.saliman.properties") version "1.5.1"
}

repositories {
    jcenter()
}

val gsqlGraphname: String by project
val gsqlHost: String by project
val gsqlUserName: String by project
val gsqlPassword: String by project
val gsqlAdminUserName: String by project
val gsqlAdminPassword: String by project
val tokenMap: LinkedHashMap<String, String> =
    linkedMapOf("graphname" to gsqlGraphname)

val grpSchema: String = "Tigergraph Schema"

tigergraph {
    scriptDir.set(file("db_scripts"))
    tokens.set(tokenMap)
    serverName.set(gsqlHost)
    userName.set(gsqlUserName)
    password.set(gsqlPassword)
    adminUserName.set(gsqlAdminUserName)
    adminPassword.set(gsqlAdminPassword)
}

tasks {
    val createSchema by registering(GsqlTask::class) {
        group = grpSchema
        description = "Create the schema on the database"
        dependsOn("dropSchema")
        scriptPath = "dbscripts/schema/schema.gsql"
        superUser = true
    }

    val dropSchema by registering(GsqlTask::class) {
        group = grpSchema
        description = "Drops the schema on the database"
        scriptPath = "dbscripts/schema/drop.gsql"
        superUser = true
    }
}