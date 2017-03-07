main:
	javac -d ./src src/a1/*.java
run: main
	java -cp ./src a1.Game
clean: 
	rm src/a1/*.class
