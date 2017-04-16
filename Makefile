JC = javac
src = $(wildcard *.java)
bin =  $(src:%.java=%)
classes = $(src:%.java=%.class)

all: $(bin) $(classes)

% : %.java
	$(JC) $<

clean :
	rm -f $(bin) $(classes) run babelfishToken.class bablefishtype.class



