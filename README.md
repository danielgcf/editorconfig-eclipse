# editorconfig-eclipse

#Import in dropins

Go to the release tab and get the .jar file.

Copy it on the eclipse/dropins folder

#Download the repository, and import it as MAVEN project:

Open eclipse and choose the workspace repository (it is the folder where goes the .editorconfig

File -> Import -> Existing Maven Project.

In the Root Directory open the folder the download repository

Place the .editorconfig file at the workspace-project (the folder that eclipse ask when it opens)

#Install locally as Plugin

Rigth Click on the project -> Export -> Deployable Plug-ins and fragments -> "Instal into host. Repository" -> Finish

After restart there will be a Menu "EditorConfig" on the top of eclipse.

Supports:
indent_style
indent_size
tab_width
trim_trailing_whitespace
insert_final_newline
end_of_line