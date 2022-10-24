```sh
nix-build wiki-search.nix
```

To use the script and inspect how Nix packaged it run a `nix-shell` which uses the package:

```sh
nix-shell shell-usage-example.nix
```

Here's a session inspecting the contents of the package:

```
[nix-shell:/workspace/random/nix/nix-build/wiki-search]$ which wiki-search
/nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin/wiki-search

[nix-shell:/workspace/random/nix/nix-build/wiki-search]$ ls -al /nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search
total 12
dr-xr-xr-x 3 gitpod gitpod   17 Jan  1  1970 .
drwxr-xr-x 1 gitpod gitpod 8192 Oct 24 14:01 ..
dr-xr-xr-x 2 gitpod gitpod   75 Jan  1  1970 bin

[nix-shell:/workspace/random/nix/nix-build/wiki-search]$ ls -al /nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin/
total 4
dr-xr-xr-x 2 gitpod gitpod  75 Jan  1  1970 .
dr-xr-xr-x 3 gitpod gitpod  17 Jan  1  1970 ..
lrwxrwxrwx 1 gitpod gitpod  71 Jan  1  1970 .wiki-search-wrapped -> /nix/store/0my4c005scab1gxy1cwx3mcv1p00pb67-wiki-search/bin/wiki-search
lrwxrwxrwx 1 gitpod gitpod  68 Jan  1  1970 curl -> /nix/store/98zjbchwflnc55i2jhvpi79aa69frsks-curl-7.84.0-bin/bin/curl
lrwxrwxrwx 1 gitpod gitpod  61 Jan  1  1970 jq -> /nix/store/wb4yxhqcysbpf6jj5cx3bhhmr6gdrl4w-jq-1.6-bin/bin/jq
-r-xr-xr-x 1 gitpod gitpod 404 Jan  1  1970 wiki-search

[nix-shell:/workspace/random/nix/nix-build/wiki-search]$ cat /nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin/wiki-search
#! /nix/store/1b9p07z77phvv2hf6gm9f28syp39f1ag-bash-5.1-p16/bin/bash -e
PATH=${PATH:+':'$PATH':'}
PATH=${PATH/':''/nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin'':'/':'}
PATH='/nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin'$PATH
PATH=${PATH#':'}
PATH=${PATH%':'}
export PATH
exec -a "$0" "/nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin/.wiki-search-wrapped"  "$@" 

[nix-shell:/workspace/random/nix/nix-build/wiki-search]$ cat /nix/store/m0vjsd6gb719mcphfgfyla6129y3nkcz-wiki-search/bin/.wiki-search-wrapped
#!/nix/store/1b9p07z77phvv2hf6gm9f28syp39f1ag-bash-5.1-p16/bin/bash
set -euo pipefail

url="https://en.wikipedia.org/w/rest.php/v1/search/page?q=${1}&limit=1"
curl "${url}" --silent | jq '.'
```
