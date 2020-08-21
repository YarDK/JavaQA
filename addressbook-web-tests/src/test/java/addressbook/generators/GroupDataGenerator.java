package addressbook.generators;

import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group counter")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException e){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));

    }


    private void save(List<GroupData> groups, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //Writer writer = new FileWriter(file);
        for (GroupData g : groups) bw.write(String.format("%s;%s;%s\n", g.getName(), g.getHeader(), g.getFooter()));
        bw.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(String.format("Test_name_%s", i + 1))
                    .withHeader(String.format("Test_header_%s", i + 1))
                    .withFooter(String.format("Test_footer_%s", i + 1))
            );
        }
        return groups;
    }
}
