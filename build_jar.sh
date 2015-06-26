set -x
set -e

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
TEMPLIB="$DIR/tmp_lib"
if [ -e "$TEMPLIB" ]; then
    rm -r "$TEMPLIB"
fi
mkdir "$TEMPLIB"
cd "$TEMPLIB"
jar -xvf ../lib/json-simple-1.1.1.jar org/
cd ..
cp  -r bin/* "$TEMPLIB"
jar -cef geoget.Main  geoget.jar -C "$TEMPLIB" .
rm -r "$TEMPLIB"

