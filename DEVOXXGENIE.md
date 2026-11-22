DEVOXXGENIE.md
Project Guidelines
Build Commands
Build: ./gradlew build
Test: ./gradlew test
Single Test: ./gradlew test --tests ClassName.methodName
Clean: ./gradlew clean
Run: ./gradlew run
Code Style
Formatting: Use IDE or Checkstyle for formatting.
Naming:
Use camelCase for variables, methods, and fields.
Use PascalCase for classes and interfaces.
Use SCREAMING_SNAKE_CASE for constants.
Documentation: Use JavaDoc for documentation.
Imports: Organize imports and avoid wildcard imports.
Exception Handling: Prefer specific exceptions and document thrown exceptions.
Minecraft Mod Development
Target Environment

All Minecraft mod development must target the following environment unless explicitly instructed otherwise:

Minecraft Version: 1.21.1
Mod Loader: NeoForge
Programming Language: Java
Java Version: Java 21
Build System: Gradle
Modding API: NeoForge 1.21.1
NeoForge Development Guidelines
Always use the NeoForge API rather than Forge or Fabric APIs.
Ensure all code is compatible with Minecraft 1.21.1.
Use Java 21 features where appropriate.
Consult the official NeoForge documentation when uncertain about API usage.
Never assume APIs from older Minecraft or NeoForge versions are still valid.
Prefer modern NeoForge conventions and recommended implementation patterns.
Avoid deprecated APIs whenever possible.
Do not mix Forge, Fabric, and NeoForge dependencies or conventions.
Project Structure

Follow the standard NeoForge mod project structure.

Typical structure:

text
src/
├── main/
│ ├── java/
│ │ └── com/example/mod/
│ │ ├── ExampleMod.java
│ │ ├── registry/
│ │ ├── item/
│ │ ├── block/
│ │ ├── entity/
│ │ ├── event/
│ │ ├── network/
│ │ ├── client/
│ │ ├── config/
│ │ └── util/
│ └── resources/
│ ├── META-INF/
│ │ └── neoforge.mods.toml
│ ├── assets/
│ │ └── modid/
│ │ ├── blockstates/
│ │ ├── models/
│ │ │ ├── block/
│ │ │ └── item/
│ │ ├── textures/
│ │ │ ├── block/
│ │ │ ├── item/
│ │ │ └── entity/
│ │ ├── lang/
│ │ └── sounds/
│ └── data/
│ └── modid/
│ ├── recipes/
│ ├── loot_tables/
│ ├── tags/
│ ├── advancements/
│ └── worldgen/

Adapt the structure when necessary, but keep related functionality organized.

Mod Initialization
Use a dedicated main mod class annotated with @Mod.
Keep the main mod class responsible for initialization and registration setup.
Separate registration logic into dedicated classes.
Use the mod event bus for mod lifecycle and registration events.
Use the NeoForge event bus for gameplay events where appropriate.
Avoid putting all mod functionality inside the main mod class.
Registration System

Use NeoForge's DeferredRegister system for registering mod content.

Organize registrations into separate classes where appropriate:

Items
Blocks
Block entities
Entities
Menus
Creative mode tabs
Sounds
Particles
Data components
Attachments
Effects

Registration guidelines:

Use DeferredRegister and DeferredHolder where appropriate.
Use descriptive registry names.
Keep registry identifiers consistent and lowercase.
Use the mod ID as the namespace.
Avoid manual registration unless specifically required by the API.
Items and Blocks

When creating custom items and blocks:

Follow Minecraft 1.21.1 item and block registration conventions.
Provide appropriate item and block properties.
Register block items when creating obtainable custom blocks.
Create appropriate block states and models.
Provide texture files and localization entries.
Implement custom behavior in dedicated classes.
Use block entities only when additional persistent or dynamic block data is required.
Use data components for custom item data instead of relying on outdated NBT patterns.
Entities and AI

When creating custom entities:

Use the appropriate NeoForge entity registration system.
Separate entity attributes, goals, and behavior into organized classes.
Use Minecraft's existing AI goal system when possible.
Register entity attributes correctly.
Implement appropriate spawn rules.
Provide entity models, textures, and rendering code.
Keep client-side rendering code separate from common/server-side logic.
Events and Gameplay Mechanics
Use NeoForge event subscribers where appropriate.
Use @SubscribeEvent for event handlers.
Clearly specify the appropriate event bus.
Avoid unnecessary event listeners.
Prefer existing Minecraft mechanics and APIs over reinventing functionality.
Ensure event handlers are efficient and avoid expensive operations every tick.
Verify event behavior on both dedicated servers and integrated servers.
Client and Server Separation

This is especially important for multiplayer compatibility.

Keep client-only code separate from common code.
Never reference client-only classes from common or dedicated-server code.
Use appropriate client setup events for renderers, screens, and key mappings.
Ensure gameplay logic executes on the correct logical side.
Perform authoritative gameplay changes on the server.
Synchronize necessary information between clients and servers.
Never assume that code running on the client is authoritative.
Networking

When implementing multiplayer features:

Use NeoForge's supported networking system for Minecraft 1.21.1.
Create clearly defined payload classes.
Register custom payload types and handlers correctly.
Validate all client-provided data on the server.
Avoid trusting client-side player state or requests.
Send synchronization packets only when necessary.
Keep packet handling efficient.
Ensure networking works in both single-player and dedicated multiplayer environments.
Data Generation

Prefer data generation over manually maintaining repetitive JSON files.

Use NeoForge's data generation system for:

Blockstates
Block models
Item models
Recipes
Loot tables
Tags
Language files
World generation data

Guidelines:

Keep generated resources reproducible.
Avoid manually editing generated files unless necessary.
Ensure generated JSON follows Minecraft 1.21.1 conventions.
Verify generated resources before considering a feature complete.
Recipes and World Generation

When implementing recipes:

Use the correct Minecraft 1.21.1 recipe formats.
Register custom recipe types and serializers only when necessary.
Prefer vanilla recipe systems when they meet the requirements.

When implementing world generation:

Use the appropriate configured and placed feature systems.
Register custom biomes, structures, features, and other world generation content correctly.
Ensure world generation is compatible with existing dimensions and terrain generation.
Avoid modifying existing world generation globally unless explicitly requested.
GUI and Menus

When implementing custom interfaces:

Use AbstractContainerMenu for inventory-backed menus.
Use AbstractContainerScreen for client-side menu rendering.
Synchronize server-side inventory and gameplay data correctly.
Validate all actions on the server.
Keep rendering logic separate from inventory and gameplay logic.
Ensure menu interactions work correctly in multiplayer.
Configuration
Use NeoForge's configuration system.
Separate client, common, and server configuration where appropriate.
Provide sensible defaults.
Validate configuration values.
Document configuration options.
Avoid hardcoding values that should be configurable.
Textures, Models, and Assets

When creating custom assets:

Follow Minecraft's resource location conventions.
Use lowercase resource identifiers.
Keep assets inside the correct assets/<modid>/ directory.
Ensure models reference valid textures.
Include localization entries for all player-facing text.
Avoid hardcoded display names.
Follow vanilla Minecraft conventions for model and texture organization.
Code Quality and Architecture
Prefer modular, reusable classes.
Follow object-oriented programming principles.
Avoid unnecessary inheritance and excessive abstraction.
Use interfaces where they improve extensibility.
Avoid giant classes responsible for unrelated functionality.
Keep registration, gameplay logic, rendering, networking, and configuration separate.
Use meaningful class, method, and variable names.
Document complex behavior and non-obvious implementation decisions.
Avoid duplicating existing Minecraft or NeoForge functionality.
Compatibility and Performance
Prioritize compatibility with Minecraft 1.21.1 and NeoForge.
Avoid unnecessary dependencies.
Minimize expensive operations during tick events.
Avoid unnecessary entity or block entity ticking.
Prevent memory leaks and unnecessary object creation.
Consider dedicated server compatibility.
Do not introduce client-only dependencies into common code.
Consider mod compatibility when modifying vanilla gameplay behavior.
Development Workflow

When implementing a new mod feature:

Understand the requested functionality before writing code.
Identify the relevant Minecraft and NeoForge APIs.
Check the official documentation and existing project conventions.
Plan the required classes, registrations, resources, and assets.
Implement the feature using modular and maintainable code.
Verify client/server separation and multiplayer compatibility.
Generate or create the required resources.
Build the project using ./gradlew build.
Fix compilation errors and warnings introduced by the changes.
Test the feature in Minecraft whenever possible.
Debugging Guidelines
Read the complete error message before attempting a fix.
Check Minecraft and NeoForge version compatibility first.
Inspect relevant Gradle dependencies and mappings.
Check registration identifiers and resource locations.
Verify event bus selection and event registration.
Check dedicated server logs for side-related issues.
Avoid introducing workarounds that hide underlying problems.
Never claim a feature works without appropriate verification.
Documentation References

Use official resources as the primary source of information:

NeoForge Documentation: https://docs.neoforged.net/
NeoForge GitHub: https://github.com/neoforged/NeoForge
Minecraft Wiki: https://minecraft.wiki/

Always prefer documentation corresponding to Minecraft 1.21.1.

General AI Development Rules
Before modifying existing code, inspect the relevant files and understand their purpose.
Preserve existing project architecture unless changes are necessary.
Do not rewrite unrelated code.
Do not remove existing functionality without explicit permission.
When creating new files, follow the existing package and naming conventions.
When modifying dependencies, explain why the change is necessary.
Do not fabricate API methods, classes, or annotations.
Verify uncertain API details against the correct NeoForge version.
Provide complete, compilable implementations rather than incomplete pseudocode when asked to implement a feature.
Clearly explain any assumptions or limitations.
Prioritize correctness, maintainability, performance, and compatibility.