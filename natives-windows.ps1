$ProjectRoot = Get-Location
$LibDir = "$ProjectRoot\lib\"
$OutputDir = "$ProjectRoot\natives\windows"

New-Item -ItemType Directory -Force -Path $OutputDir | Out-Null

Set-Location $LibDir

Get-ChildItem *natives-windows*.jar | ForEach-Object {
    Write-Host "Extracting $($_.Name)"
    jar xf $_.FullName
}

Get-ChildItem -Recurse -Filter "*.dll" | ForEach-Object {
    Move-Item -Path $_.FullName -Destination $OutputDir -Force
}

Write-Host "Done! Extracted natives to $OutputDir"

Set-Location $ProjectRoot
