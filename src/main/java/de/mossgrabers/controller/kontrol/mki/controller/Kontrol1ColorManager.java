// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2020
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.controller.kontrol.mki.controller;

import de.mossgrabers.framework.controller.ButtonID;
import de.mossgrabers.framework.controller.color.ColorEx;
import de.mossgrabers.framework.controller.color.ColorManager;
import de.mossgrabers.framework.controller.grid.IPadGrid;
import de.mossgrabers.framework.daw.DAWColor;
import de.mossgrabers.framework.scale.Scales;
import de.mossgrabers.framework.view.AbstractDrumView;
import de.mossgrabers.framework.view.AbstractPlayView;


/**
 * Different colors to use for the key LEDs of the Kontrol 1.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class Kontrol1ColorManager extends ColorManager
{
    private static final int DAW_COLOR_START          = 100;

    private static final int SCALE_COLOR_OCTAVE       = 50;
    private static final int SCALE_COLOR_NOTE         = 51;
    private static final int SCALE_COLOR_OUT_OF_SCALE = 52;
    private static final int COLOR_PLAY               = 53;
    private static final int COLOR_RECORD             = 54;


    /**
     * Constructor.
     */
    public Kontrol1ColorManager ()
    {
        this.registerColorIndex (ColorManager.BUTTON_STATE_OFF, 0);
        this.registerColorIndex (ColorManager.BUTTON_STATE_ON, 6);
        this.registerColorIndex (ColorManager.BUTTON_STATE_HI, 255);

        this.registerColorIndex (Scales.SCALE_COLOR_OFF, 0);
        this.registerColorIndex (Scales.SCALE_COLOR_OCTAVE, SCALE_COLOR_OCTAVE);
        this.registerColorIndex (Scales.SCALE_COLOR_NOTE, SCALE_COLOR_NOTE);
        this.registerColorIndex (Scales.SCALE_COLOR_OUT_OF_SCALE, SCALE_COLOR_OUT_OF_SCALE);

        this.registerColorIndex (AbstractPlayView.COLOR_OFF, 0);

        this.registerColorIndex (AbstractDrumView.COLOR_PAD_PLAY, COLOR_PLAY);
        this.registerColorIndex (AbstractDrumView.COLOR_PAD_RECORD, COLOR_RECORD);

        this.registerColorIndex (IPadGrid.GRID_OFF, 0);

        final DAWColor [] values = DAWColor.values ();
        for (int i = 0; i < values.length; i++)
            this.registerColorIndex (values[i], DAW_COLOR_START + i);
    }


    /** {@inheritDoc} */
    @Override
    public ColorEx getColor (final int colorIndex, final ButtonID buttonID)
    {
        if (buttonID == null)
        {
            if (colorIndex >= DAW_COLOR_START)
                return DAWColor.getColorEntry (colorIndex - DAW_COLOR_START);

            switch (colorIndex)
            {
                case SCALE_COLOR_OCTAVE:
                    return ColorEx.BLUE;
                case SCALE_COLOR_NOTE:
                    return ColorEx.WHITE;
                case SCALE_COLOR_OUT_OF_SCALE:
                    return ColorEx.BLACK;
                case COLOR_PLAY:
                    return ColorEx.GREEN;
                case COLOR_RECORD:
                    return ColorEx.RED;
                default:
                    return ColorEx.BLACK;
            }
        }

        switch (buttonID)
        {
            case PLAY:
                return colorIndex == 255 ? ColorEx.GREEN : ColorEx.DARK_GREEN;
            case RECORD:
                return colorIndex == 255 ? ColorEx.RED : ColorEx.DARK_RED;
            default:
                return colorIndex == 255 ? ColorEx.WHITE : ColorEx.DARK_GRAY;
        }
    }
}