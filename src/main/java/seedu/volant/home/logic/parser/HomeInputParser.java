package seedu.volant.home.logic.parser;

import static seedu.volant.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.volant.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.volant.commons.logic.parser.Parser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.volant.commons.logic.commands.BackCommand;
import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.ExitCommand;
import seedu.volant.commons.logic.commands.GotoCommand;
import seedu.volant.commons.logic.commands.HelpCommand;
import seedu.volant.commons.logic.parser.GotoCommandParser;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.home.logic.commands.AddCommand;
import seedu.volant.home.logic.commands.ClearCommand;
import seedu.volant.home.logic.commands.DeleteCommand;
import seedu.volant.home.logic.commands.EditCommand;
import seedu.volant.home.logic.commands.FindCommand;
import seedu.volant.home.logic.commands.ListCommand;

/**
 * Parses user input when on the HOME page.
 */
public class HomeInputParser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch(commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GotoCommand.COMMAND_WORD:
            return new GotoCommandParser().parse(arguments);

        case BackCommand.COMMAND_WORD:
            return new BackCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
