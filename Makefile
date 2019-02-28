default: none

none:
	echo "Requires argument"

clean:
	rm -rf data build

versioncheck:
	./gradlew dependencyUpdates
