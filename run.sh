#!/bin/bash

echo "========= AI BASIC PROJECTS ========="
echo "1: Glasses problem"
echo "2: Rush Hours"
echo "3: Sorting problem"
echo "0: Exit"

path_to_jar=".:`pwd`/aima0.95.jar"
read -p "#: " choice

case $choice in
	1) 	cd GlassesProblem
		java -cp "$path_to_jar" "GlassMain";;
	2) 	cd ParkingProblem
		java -cp "$path_to_jar" "ParkingMain";;
	3) 	cd SortProblem
		java -cp "$path_to_jar" "SortMain";;
	0)  exit 0;;
	*) echo "Choice not valid"
	   exit 1
	   ;;
esac
# read -p "Press Enter key to continue..."