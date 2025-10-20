#!/bin/bash

PROJECT_ROOT="$(pwd)"
LWJGL_DIRECTORY="$PROJECT_ROOT/lib/"
OUTPUT_DIRECTORY="$PROJECT_ROOT/natives/linux"

mkdir -p "$OUTPUT_DIRECTORY"

cd "$LWJGL_DIRECTORY" || { echo "LWJGL directory not found!"; exit 1; }

for jar in *natives-linux*.jar; do
  echo "Extracting $jar"
  jar xf "$jar"
done

find "$LWJGL_DIRECTORY" -type f -name "*.so" -exec mv -v {} "$OUTPUT_DIRECTORY" \;

echo "Done! Natives extracted to $OUTPUT_DIRECTORY"
