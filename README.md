# Scripts to load UMLS into TigerGraph using Giraffle Gradle plugin

To use this script you will need to have a local copy of UMLS on a file system.
You must registher with the [US NIH](https://www.nlm.nih.gov/research/umls/index.html) and then download a 5GB compressed file.  For example [here](https://www.nlm.nih.gov/research/umls/licensedcontent/umlsknowledgesources.html) is the licensed UMLS page with the donwloads for the full version of UMLS.

Your local system must also have [gradle installed](https://gradle.org/install/).

Once you have gradle installed (test by typing $ gradle version) you can type:

```$ gradle tasks```

This will list a set of task to load the schema and load all UMLS data.

See [Giraffle](https://optum.github.io/giraffle/#/) documentation for further details.
[Description of UMLS Tables](https://www.ncbi.nlm.nih.gov/books/NBK9685/#_ch03_sec3_3_)
[Diagram of UMLS Tables](https://www.nlm.nih.gov/research/umls/images/meta_e_diagram.jpg)


