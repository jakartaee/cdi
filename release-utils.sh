#!/bin/sh

set -o errexit

SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ] ; do SOURCE="$(readlink "$SOURCE")"; done
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

# DEFINE

SNAPSHOT_REPO_URL="https://repository.jboss.org/nexus/content/repositories/snapshots/"
SNAPSHOT_REPO_ID="jboss-snapshots-repository"
RELEASE_REPO_URL="https://repository.jboss.org/nexus/service/local/staging/deploy/maven2/"
RELEASE_REPO_ID="jboss-releases-repository"

# SCRIPT

usage()
{

HUMAN_READABLE_ARCHETYPES=""
i=0
for archetype in $ARCHETYPES
do
   if [ $i -ne 0 ]
   then
      HUMAN_READABLE_ARCHETYPES="${HUMAN_READABLE_ARCHETYPES}, "
   fi
   echo ""
   HUMAN_READABLE_ARCHETYPES="${HUMAN_READABLE_ARCHETYPES}${archetype}"
   i=$[$i+1]
done


cat << EOF
usage: $0 options

This script aids with releases of the CDI API.

OPTIONS:
   -u      Updates version numbers in all POMs, used with -o and -n
   -o      Old version number to update from
   -n      New version number to update to
   -s      Deploy a snapshot of the CDI API
   -r      Deploy a release of the CDI API
   -h      Shows this message

EOF
}

update()
{
cd $DIR
echo "Updating versions from $OLDVERSION TO $NEWVERSION for all Java and XML files under $PWD"
perl -pi -e "s/${OLDVERSION}/${NEWVERSION}/g" `find . -name \*.xml -or -name \*.java`
}

snapshot()
{
   echo "\n**** Deploying $archetype to ${SNAPSHOT_REPO_URL} \n"
   mvn clean javadoc:jar deploy  -f api/pom.xml -DaltDeploymentRepository=${SNAPSHOT_REPO_ID}::default::${SNAPSHOT_REPO_URL}
}

release()
{
   echo "\n**** Deploying $archetype to ${RELEASE_REPO_URL} \n"
   mvn clean javadoc:jar deploy -f api/pom.xml -DaltDeploymentRepository=${RELEASE_REPO_ID}::default::${RELEASE_REPO_URL}
}

OLDVERSION="1.0.0-SNAPSHOT"
NEWVERSION="1.0.0-SNAPSHOT"
CMD="usage"
DEST=""

while getopts “sruo:n:” OPTION

do
     case $OPTION in
         u)
             CMD="update"
             ;;
         h)
             usage
             exit
             ;;
         o)
             OLDVERSION=$OPTARG
             ;;
         n)
             NEWVERSION=$OPTARG
             ;;
         s)
             CMD="snapshot"
             ;;
         r)  
             CMD="release"
             ;;
         [?])
             usage
             exit
             ;;
     esac
done

$CMD

