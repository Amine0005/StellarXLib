package it.vamxneedev.stellarxlib.builders.command;

public class CommandBuilder {
    private String command;

    public CommandBuilder(String baseCommand) {
        this.command = baseCommand;
    }

    public CommandBuilder addArgument(String argument) {
        this.command += " " + argument;
        return this;
    }

    public CommandBuilder addFlag(String flag) {
        this.command += " -" + flag;
        return this;
    }

    public String build() {
        return command;
    }
}
