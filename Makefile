JC = javac
src = $(wildcard *.java)
classes = $(src:.java=.class)

all: $(classes)

clean :
	rm -f *.class

%.class : %.java
	$(JC) $<
