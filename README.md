# Matter Overdrive

<h1 align="center">
Yeah hi I obviously don't own Matter Overdrive go look at the guy that made it, this is literally a hack to remove some parts and provide bugfixes to 1.7
</h1>

<h2 align="center">
<a href='https://mo.simeonradivoev.com/'>Website</a>
</h2>

## Table of Contents
* [About](#about)
* [Features](#features)
* [Isues](#issues)
* [Building](#building)
* [IMC](#imc)

## About
Matter Overdrive is a Minecraft mod inspired by the popular Sci-fi TV series Star Trek. It dwells in the concept of replicating and transforming one type matter into another.
Although it may seem overpowered, Matter Overdrive takes a more realistic approach and requires the player to build a complex system before even the simplest replication can be achieved.
It's pretty neat you should go see the guy that made it and give him your money because he's aight I guess.

## Features
* [Matter Scanner](https://mo.simeonradivoev.com/items/matter_scanner/), for scanning matter patterns for replication.
* [Replicator](https://mo.simeonradivoev.com/items/replicator/), for transforming materials.
* [Decomposer](https://mo.simeonradivoev.com/items/decomposer/), for braking down materials to basic form.
* [Transporter](https://mo.simeonradivoev.com/items/transporter/), for beaming up.
* [Phaser](https://mo.simeonradivoev.com/items/phaser/), to set on stun.
* [Fusion Reactors](https://mo.simeonradivoev.com/fusion-reactor/) and [Gravitational Anomaly](https://mo.simeonradivoev.com/items/gravitational_anomaly/)
* Complex Networking for replication control.
* Star Maps, with Galaxies, Stars and Planets
* [Androids](https://mo.simeonradivoev.com/android-guide/), become an Android and learn powerful RPG like abilities, such as Teleportation and Forefield Shields.

## Issues
If you have any crashes, problems or suggestions just open a [new Issue](https://github.com/xenoswift/MatterOverdrive/issues/new).
If your crash or problem was fixed, but is not yet released as a public download you can always try building the latest commit yourself.

## Building
1. Clone this repository.
2. Setup workspace.
  - Decompiled source `gradlew setupDecompWorkspace`
  - Obfuscated source `gradlew setupDevWorkspace`
3. Build `gradlew build`. Jar will be in `build/libs`
4. For core developer: Setup IDE
  - IntelliJ: Import into IDE and execute `gradlew genIntellijRuns` afterwards
  - Eclipse: execute `gradlew eclipse`
## Contributing ?
1. Clone this repo.
2. Setup workspcae.
  - gradlew setupDecompWorkspace
  - gradlew idea (or eclipse if you like i suppose)
  - pray
Should be good to go.
3. ???
4. Make a separate branch entirely before you modify things to contribute, that way you submit a PR and I can fold in your branch instead of trying to mesh your changes with my autism.
  
## IMC
See the example on [IMC](https://github.com/simeonradivoev/MatterOverdrive/blob/master/src/main/java/matteroverdrive/api/IMC.java) or you can see the [IMC handler](https://github.com/simeonradivoev/MatterOverdrive/blob/master/src/main/java/matteroverdrive/imc/MOIMCHandler.java).