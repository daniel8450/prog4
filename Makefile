JC = javac
src = $(wildcard *.java)
bin =  $(src:%.java=%)
classes = $(src:%.java%.class)

all: $(bin)

clean :
	rm -f *.class

%.class : %.java
	$(JC) $<
	
	src = $(wildcard *.java)
bin = $(src:%.java=%)
hi  = $(src:%.java=%.hi)
obj = $(src:%.java=%.o)

all : $(bin)

% : %.java
	javac $@

clean:
	rm -f $(bin) $(hi) $(obj) run
