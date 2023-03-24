# unproject-seed-generator

Since [`untemplate-seed.g8`](https://github.com/swaldman/untemplate-seed.g8) and
[`unstatic-seed.g8`](https://github.com/swaldman/untemplate-seed.g8) share a lot and will likely often want to be
updated in sync (as [`untemplate`](https://github.com/swaldman/untemplate) or [`mill`](https://github.com/com-lihaoyi/mill) versions change, for example), this
project generates those projects and updates both directpries.

The shell script `./rebuild.sh` will regenerate the seed inside this repository as `seeds-out`.

The shell script `./rebuild-and-merge.sh` does the same, but merges the result into directories `../untemplate-seed.g8` and `../unstatic-seed.g8`.

For now, to publish the seeds you must then manually `cd` into the updated directories, commit and push to github.




