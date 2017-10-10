#!/usr/bin/env bash

# Update the repository
sudo apt-get update

# Install the core essentials
sudo apt-get install --yes build-essential curl file git python-setuptools ruby emacs-nox vim

# Install Linuxbrew
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Linuxbrew/install/master/install)"
test -d ~/.linuxbrew && PATH="$HOME/.linuxbrew/bin:$PATH"
test -d /home/linuxbrew/.linuxbrew && PATH="/home/linuxbrew/.linuxbrew/bin:$PATH"
test -r ~/.bash_profile && echo 'export PATH="$(brew --prefix)/bin:$PATH"' >>~/.bash_profile
echo 'export PATH="$(brew --prefix)/bin:$PATH"' >>~/.profile

# Reload the profile
source ~/.profile

# Install the Linuxbrew wrapper
sudo apt install --yes linuxbrew-wrapper

# Install the core dependencies
brew install htop
brew install tmux
brew install maven
brew install gcc

# Install python
brew install python3
pip3 install ipython ipdb

# Install go
brew install go
echo 'export PATH=$PATH:$HOME/.linuxbrew/opt/go/libexec/bin' >>  ~/.profile
echo 'export GOPATH=/repo' >> ~/.profile
source ~/.profile

# Install jdk
brew install jdk

# Install node
brew install nvm
echo 'export NVM_DIR="$HOME/.nvm"' >> ~/.profile
echo '. "$HOME/.linuxbrew/opt/nvm/nvm.sh"' >> ~/.profile
source ~/.profile
nvm install node

# Install gtop
npm install gtop -g

# Installl pthreads
sudo apt-get install --yes libpthread-stubs0-dev
