package name.subroutine.game24trainer.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

import static name.subroutine.game24trainer.puzzle.PuzzleTag.*;

import name.subroutine.game24trainer.Game24Analyzer;
import name.subroutine.game24trainer.puzzle.Puzzle;
import name.subroutine.game24trainer.puzzle.SolutionSet;

public class ServicePracticeSheetTests
{
    ServicePracticeSheet sut = new ServicePracticeSheet();

    Game24Analyzer analyzer = mock( Game24Analyzer.class );

    @Before
    public void configSut()
    {
        ReflectionTestUtils.setField( sut, "analyzer", analyzer );
    }

    @Test
    public void testOneDot() throws Exception
    {
        int count = 1;
        SolutionSet ss = new SolutionSet();
        Puzzle p = new Puzzle( "6 6 6 6" );
        ss.setPuzzle( p );

        when( analyzer.getSolutionSetListByTags( count, ONE_DOT ) )
            .thenReturn( Collections.singletonList( ss ) );

        Map<String, String> param = new HashMap<>();
        param.put( "ONE_DOT", "1" );
        String result = sut.practiceSheet( param );

        // can't really test output since it is such a long string...
        assertThat( result, not( isEmptyString() ) );
        // the best we can do is to verify we called getSolutionSetListByTags
        verify( analyzer ).getSolutionSetListByTags( count, ONE_DOT );
    }
}
