import com.optum.giraffle.tasks.GsqlTask

plugins {
    id("com.optum.giraffle") version "1.3.3"
    id("net.saliman.properties") version "1.5.1"
}

repositories {
    jcenter()
}

// change once the binary is in maven central
dependencies {
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

// the group of tasks related to loading the schema
val grpSchema: String = "Tigergraph Schema"
// the group of tasks related to loading data
val grpLoadData: String = "Tigergraph Load"
val grpTest: String = "Tigergraph Test"
// location of the GSQL script files
val scriptDir: String = "db_scripts"
val inputDataDir: String = "/tg/dropzone"

val gsqlClientVersion: String = "3.0"

tigergraph {
    scriptDir.set(file(scriptDir))
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
        scriptPath = "schema/schema.gsql"
        superUser = true
    }

    val dropSchema by registering(GsqlTask::class) {
        group = grpSchema
        description = "Drops the schema on the database"
        scriptPath = "schema/drop.gsql"
        superUser = true
    }

    val loadSemanticGroups by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the Semantic Groups File: semantic-groups.txt"
        scriptPath = "load/semantic-groups.gsql"
        superUser = true
    }

    val loadSemanticTypes by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the Semantic Types File: RMSTY.RRF"
        scriptPath = "load/semantic-types.gsql"
        superUser = true
    }

    val loadConcepts by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the main Concepts file: MRCONS.RRF"
        scriptPath = "load/concepts.gsql"
        superUser = true
    }

    val loadRelationships by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the main Relationships file: MRREL.RRF"
        scriptPath = "load/relationships.gsql"
        superUser = true
    }

    val loadDefinitions by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the defintions file: MRDEF.RRF"
        scriptPath = "load/definitions.gsql"
        superUser = true
    }

    val loadSourceMetadata by registering(GsqlTask::class) {
        group = grpLoadData
        description = "Loads the source metadata file: MRSAB.RRF"
        scriptPath = "load/source-metadata.gsql"
        superUser = true
    }

    
   /* val echoProperties by registering {
        group = grpTest
        description = "Echo the properties"
        println("List of Properties")
        println("gsqlGraphname: " + gsqlGraphname)
        println("scriptDir: " + scriptDir)
        println("gsqlClientVersion: " + gsqlClientVersion)
        println("inputDataDir: " + inputDataDir)
    }

    val lastTask by registering {
        println("last task")
    } */
}

tasks.create<Copy>("copySemGrp") {
        description = "Copies sources to the dest directory"
        group = "Custom"
        println("Copy Sem Group File")
        from("./data/semantic-groups-pipe.txt")
        into("/tg/dropzone")
}

/* tasks.create<Exec>("countInputLines") {
    group = "grpTest"
    workingDir = <directory>/tg/dropzone
    commandLine = <String>"du -h /tg/dropzone/*"
} */