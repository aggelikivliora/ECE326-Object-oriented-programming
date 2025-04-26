COMPILE:
javac -cp ".:../libs/json-20250107.jar" -d bin  InternalNode.java MinMax.java minMaxTree.java Node.java minMaxOptimalTree.java

RUN:
java -cp ".:../libs/json-20250107.jar:bin" MinMaxAlg.MinMax