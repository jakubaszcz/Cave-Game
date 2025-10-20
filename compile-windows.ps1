$SRC = "src\cavegame\Main.java"
$OUT = "out"
$LIB = "lib"
$NATIVES = "natives\windows"
$MAINCLASS = "cavegame.Main"

if (-not (Test-Path $OUT)) {
    New-Item -ItemType Directory -Path $OUT | Out-Null
}

javac -cp "$LIB\*;." -d $OUT $SRC

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!"
    exit
}

java -cp "$LIB\*;$OUT" "-Djava.library.path=$NATIVES" $MAINCLASS
