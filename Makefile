run:
	@rm -rf build/*
	@javac -sourcepath edit -d build edit/*.java 
	@java -cp build edit.MainWindow
