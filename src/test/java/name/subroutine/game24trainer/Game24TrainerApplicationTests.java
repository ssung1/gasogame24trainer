package name.subroutine.game24trainer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles( "test" )
public class Game24TrainerApplicationTests {

    @Autowired
    Game24Analyzer analyzer;

    @Resource(name = "cards")
    Game24PuzzleSource source;

	@Test
	public void contextLoads()
    {
        assertThat( analyzer, is( notNullValue() ) );
	}

	@Test
    public void puzzleSourceLoads()
    {
        assertThat( analyzer.getSolver(), is( notNullValue() ) );
    }
}
