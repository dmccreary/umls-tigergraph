#!/bin/sh
# awk script that counts the distint values in the second colum of a file
# Author: Dan McCreary, March 2020
awk 'BEGIN { FS = "|" } ; { print $2 }' ../data/semantic-groups-pipe.txt | uniq | wc -l

