src = $(wildcard *.java)
bin = $(src:%.java=%)
hi  = $(src:%.java=%.hi)
obj = $(src:%.java=%.o)

all : $(bin)

% : %.java
	javac $@

clean:
	rm -f $(bin) $(hi) $(obj) run
