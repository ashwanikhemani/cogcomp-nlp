/**
 * This software is released under the University of Illinois/Research and Academic Use License. See
 * the LICENSE file in the root folder for details. Copyright (c) 2016
 *
 * Developed by: The Cognitive Computation Group University of Illinois at Urbana-Champaign
 * http://cogcomp.org/
 */
package edu.illinois.cs.cogcomp.edison.features.lrec;

import edu.illinois.cs.cogcomp.edison.features.factory.CorelexFeatureExtractor;
import org.apache.commons.lang.ArrayUtils;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.View;
import edu.illinois.cs.cogcomp.core.io.IOUtils;
import edu.illinois.cs.cogcomp.edison.features.Feature;
import edu.illinois.cs.cogcomp.edison.utilities.EdisonException;
import junit.framework.TestCase;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Test class for Corlex Feature Extractor
 *
 * @author Xiaodong Yu
 */
public class TestCorlex extends TestCase {
    static Logger log = Logger.getLogger(TestCorlex.class.getName());

    private static List<TextAnnotation> tas;

    static {
        try {
            tas = IOUtils.readObjectAsResource(TestCorlex.class, "test.ta");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public final void test() throws EdisonException {
        log.debug("Corlex Feature Extractor");
        // Using the first TA and a constituent between span of 30-40 as a test
        TextAnnotation ta = tas.get(1);
        View TOKENS = ta.getView("TOKENS");

        log.debug("Got tokens FROM TextAnnotation");

        CorelexFeatureExtractor testInstance = new CorelexFeatureExtractor(true);

        Set<Feature> feats = testInstance.getWordFeatures(ta,1);
        String[] expected_outputs =
                {"atr"};

        if (feats == null) {
            log.debug("Feats are returning NULL.");
        }

        log.debug("Printing Set of Features");
        for (Feature f : feats) {
            log.debug(f.getName());
            assertTrue(ArrayUtils.contains(expected_outputs, f.getName()));
        }


    }

}
