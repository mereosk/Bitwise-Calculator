JFLAGS = -g
JC = javac


compile:
	$(JC) $(JFLAGS) *.java

clean:
	$(RM) *.class