## Terminology
* *Git* - an open source program for tracking changes in text files, and is the core technology that GitHub, the social and user interface, is built on top of.
* *Branch* - a parallel version of a repository. It is contained within the repository, but does not affect the primary or master branch allowing you to work freely without disrupting the "live" version. 
 
## Feature branching
* check branches `git branch`.
If there are no branches, the result is:
```
* master
```
* create a new branch `git checkout -b feature-branch`. <br />This command does two things:
  * it creates a new local metrics branch
  * it switches the working folder to reference the newly created branch
```
Switched to a new branch 'feature-branch'
```
