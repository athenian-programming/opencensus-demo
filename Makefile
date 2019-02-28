default: .build

.build:
	./gradlew assemble

clean:
	rm -rf data build

versioncheck:
	./gradlew dependencyUpdates
