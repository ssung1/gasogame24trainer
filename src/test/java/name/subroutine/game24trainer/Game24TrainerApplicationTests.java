package name.subroutine.game24trainer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
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
