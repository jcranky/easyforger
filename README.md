EasyForger
==========

[![Join the chat at https://gitter.im/easyforger/easyforger](https://badges.gitter.im/easyforger/easyforger.svg)](https://gitter.im/easyforger/easyforger?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Note: this is still under heavy development and everything is subject to change!

Also, feedback is very much appreciated. A good place to send them is our mailing list at https://groups.google.com/group/easyforger

For a list of what changes as the releases progresses, have a look at the [Changelog](Changelog.md).

EasyForger is a DSL to help you write MinecraftForge mods for Minecraft (obviously =p)

To run the project, follow these steps:

* clone the repository
* setup your gradle environment with this command: ```./gradlew setupDecompWorkspace```
* you can make sure everything is really working with ```./gradlew runClient``` - this should launch minecraft with your mods
* import the project into your IDE
* for IDEA, after importing, run this command: ```./gradlew genIntellijruns```
* run ```Minecraft Client``` from your IDE menu (which is generated in the command above)

For detailed information have a look at the Minecraft Forge documentation here: http://www.minecraftforge.net/wiki/Installation/Source

Also, please keep in mind that EasyForger uses Scala, so you should have your IDE's Scala plugin installed.

--

### Know limitations and awkward behaviours

* When creating shaped recipes, the short for `Items.wheat_seeds` is `s`, not `w`


This library is licensed under the LGPL v3.


### Sample mods

Please have a look in the _mods_ sub project to find a few sample mods developed using easyforger.
