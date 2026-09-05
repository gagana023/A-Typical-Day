# Final project for Advanced Programming Topics

See directions for final project [here](https://nchs-cs.github.io/advanced-topics/final-project/)

All documentation for this project can be found [here](doc/README.md)

Claude was used to help us debug our issues, mainly the ones tending to having God classes that we learned from our pmd. We also used it in the inital phase to help us plan out our class structure.

ChatGPT was used to help us understand our errors or the messages we would get from the PMD and the CPD and that helped us understand what to do when breaking up our classes.

Stack Overflow was used to design the PANIC feature because we originally did not know how to make the actual screen shake.

## AI dialogue setup

Typed player responses are classified through OpenAI. Start the `GameEngine` or `Run Jar File`
debug configuration in VS Code and enter the API key when the masked prompt appears. The key is
passed only to that launch and is not stored in the project.

For terminal launches, set the API key for the current PowerShell session:

```powershell
$env:OPENAI_API_KEY = "your-api-key"
code .
```

Run `code .` from the same PowerShell window after setting the variable if you want the debugger to
inherit it directly.

The optional `OPENAI_MODEL` variable selects a model and defaults to `gpt-4o-mini`. The API key is
read from the environment and is not stored in the project.
