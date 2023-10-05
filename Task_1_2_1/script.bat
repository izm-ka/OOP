javac src/main/java/ru/nsu/izmailova/heapsort/Heapsort.java -d ./build
javadoc -d build/docs -sourcepath src/main/java -subpackages ru.nsu.izmailova.heapsort
java -cp ./build ru.nsu.izmailova.heapsort.Heapsort