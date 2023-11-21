javac src/main/java/ru/nsu/izmailova/tree/Tree.java -d ./build
javadoc -d build/docs -sourcepath src/main/java -subpackages ru.nsu.izmailova.tree
java -cp ./build ru.nsu.izmailova.tree.Tree