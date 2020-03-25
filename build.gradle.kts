import com.optum.giraffle.tasks.GsqlTask
import org.apache.tools.ant.filters.ConcatFilter
import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.file.CopySpec

tasks.register<GsqlTask>("createQueryConceptHierarchy") {
    description = "Installs Concept Hierarchy Query"
    group = "UMLS Queries"

    scriptPath = "query/concept_hierarchy.gsql"
}

tasks.register<GsqlTask>("createQueryCui") {
    description = "Installs Query to pull hierarchy of concept given cui"
    group = "UMLS Queries"

    scriptPath = "query/cui_query.gsql"
}

tasks.register<GsqlTask>("createQuerySemTypeExtract") {
    description = "Installs Query to pull concepts from semantic type"
    group = "UMLS Queries"

    scriptPath = "query/semtype_extract.gsql"
}

tasks.register<GsqlTask>("createLoadAtom") {
    description = "Creates a load job to load UMLS Atoms and Concepts"
    group = "UMLS Tigergraph Load"

    scriptPath = "load/create/create_load_atoms.gsql"
}

tasks.register<GsqlTask>("createLoadSty") {
    description = "Creates a load job to load UMLS Semantic Types"
    group = "UMLS Tigergraph Load"

    scriptPath = "load/create/create_load_sty.gsql"
}

tasks.register<GsqlTask>("createLoadLabels") {
    description = "Creates a load job to load UMLS Concept Labels"
    group = "UMLS Tigergraph Load"

    scriptPath = "load/create/create_load_conceptLabels.gsql"
}

tasks.register<GsqlTask>("createLoadRank") {
    description = "Creates a load job to load UMLS Ranks"
    group = "UMLS Tigergraph Load"

    scriptPath = "load/create/create_load_rank.gsql"
}

tasks.register<GsqlTask>("createLoadRel") {
    description = "Creates a load job to load UMLS Semantic Types"
    group = "UMLS Tigergraph Load"

    scriptPath = "load/create/create_load_rels.gsql"
}

tasks.register<GsqlTask>("runLoadRel") {
    description = "Run load rels"
    group = "Tigergraph Load"

    scriptPath = "load/run_rel.gsql"
}

tasks.register<GsqlTask>("runLoadAtom") {
    description = "Run load Atoms (MRCONSO.RRF)"
    group = "Tigergraph Load"

    scriptPath = "load/run_atom.gsql"
}

tasks.register<GsqlTask>("runLoadLabels") {
    description = "Run load Concept Labels"
    group = "Tigergraph Load"

    scriptPath = "load/run_conceptLabels.gsql"
}

tasks.register<GsqlTask>("runLoadRank") {
    description = "Run load Atoms (MRRANK.RRF)"
    group = "Tigergraph Load"

    scriptPath = "load/run_rank.gsql"
}

tasks.register<GsqlTask>("runLoadSty") {
    description = "Run load SemanticTypes"
    group = "Tigergraph Load"

    scriptPath = "load/run_sty.gsql"
}

tasks.register<GsqlTask>("schemaAddEdges") {
    description = "Adds Edge types to for UMLS to Global graph"
    group = "UMLS Schema"
    superUser = true
    scriptPath = "schema/schema_add_rels.gsql"
}

tasks.register<GsqlTask>("schemaAddStrings") {
    description = "Adds String and String Indexes UMLS to Global graph"
    group = "UMLS Schema"
    superUser = true
    scriptPath = "schema/schema_add_index.gsql"
}

tasks.register<GsqlTask>("schemaAddRank") {
    description = "Adds Rank Vertex and Edges to for UMLS to Global graph"
    group = "UMLS Schema"
    superUser = true
    scriptPath = "schema/schema_add_rank.gsql"
}

tasks.register<GsqlTask>("schemaAddVertexs ") {
    description = "Adds Base Vertex and Edges to for UMLS to Global graph"
    group = "UMLS Schema"
    superUser = true
    scriptPath = "schema/schema.gsql"
}

tasks.gsqlCopySources {
    doLast{
        project.copy{
            from("db_scripts/schema/schema.gsql"){
                filter<ConcatFilter>("append" to file("db_scripts/schema/schema_add_rels.gsql"))
                filter<ConcatFilter>("append" to file("db_scripts/schema/schema_add_index.gsql"))
                filter<ConcatFilter>("append" to file("db_scripts/schema/schema_add_rank.gsql"))

                filter<ReplaceTokens>("tokens" to tigergraph.tokens)
            }
        }
    }
}


