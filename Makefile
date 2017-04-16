JC = javac
src = $(wildcard *.java)
bin =  $(src:%.java=%)
classes = $(src:%.java=%.class)

all: $(bin) $(classes)

clean :
	rm -f $(bin) $(classes) run

%.class : %.java
	$(JC) $<

