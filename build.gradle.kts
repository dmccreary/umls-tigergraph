import com.optum.giraffle.tasks.GsqlTask

plugins {
    id("com.optum.giraffle") version "1.3.3"
    id("net.saliman.properties") version "1.5.1"
}

repositories {
    jcenter()
}

dependencies {
    // gsqlRuntime("com.tigergraph.client:gsql_client:3.0")
    gsqlRuntime(files("./lib/gsql_client.jar"))
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
val grpLoad: String = "Tigergraph Load"

val gsqlClientVersion: String = "3.0"

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

    val loadSemanticGroups by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the Semantic Groups File: semantic-groups.txt"
        scriptPath = "dbscripts/load/semantic-groups.gsql"
        superUser = true
    }

    val loadSemanticTypes by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the Semantic Types File: RMSTY.RRF"
        scriptPath = "dbscripts/load/semantic-types.gsql"
        superUser = true
    }

    val loadConcepts by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the main Concepts file: MRCONS.RRF"
        scriptPath = "dbscripts/load/concepts.gsql"
        superUser = true
    }

    val loadRelationships by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the main Relationships file: MRREL.RRF"
        scriptPath = "dbscripts/load/relationships.gsql"
        superUser = true
    }

    val loadDefinitions by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the defintions file: MRDEF.RRF"
        scriptPath = "dbscripts/load/definitions.gsql"
        superUser = true
    }

    val loadSourceMetadata by registering(GsqlTask::class) {
        group = grpLoad
        description = "Loads the source metadata file: MRSAB.RRF"
        scriptPath = "dbscripts/load/source-metadata.gsql"
        superUser = true
    }
}