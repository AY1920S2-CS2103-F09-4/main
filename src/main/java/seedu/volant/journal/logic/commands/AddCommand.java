package seedu.volant.journal.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_FEELING;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.volant.commons.logic.parser.CliSyntax.PREFIX_WEATHER;

import seedu.volant.commons.logic.commands.Command;
import seedu.volant.commons.logic.commands.CommandResult;
import seedu.volant.commons.logic.commands.exceptions.CommandException;
import seedu.volant.commons.model.Model;
import seedu.volant.journal.model.JournalModelManager;
import seedu.volant.journal.model.entry.Entry;
import seedu.volant.journal.model.entry.SortType;

/**
 * Adds an entry to the Journal.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entry to the journal. \n"
            + "Parameters: \n"
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_TEXT + "TEXT "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_FEELING + "FEELING] "
            + "[" + PREFIX_WEATHER + "WEATHER]\n"

            + "Example: \n"
            + COMMAND_WORD + " "
            + PREFIX_DATE + "23-09-2018 "
            + PREFIX_TIME + "09:35 "
            + PREFIX_TEXT + "I just went skydiving!! "
            + PREFIX_LOCATION + "Brisbane "
            + PREFIX_FEELING + "SCARED "
            + PREFIX_WEATHER + "SUNNY";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the journal.";

    private final Entry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Entry}
     */
    public AddCommand(Entry entry) {
        requireNonNull(entry);
        toAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        JournalModelManager journalModel = ((JournalModelManager) model);
        if (journalModel.hasEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        journalModel.addEntry(toAdd);
        journalModel.sortEntries(SortType.NEW);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

