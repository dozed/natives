#!/bin/bash

git clone https://github.com/scalaz/scalaz.git
cd scalaz
git checkout tags/v7.2.4
git checkout -b v7.2.4
patch -p1 < ../scalaz-scala-native.patch
cd ..
