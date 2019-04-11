package com.businessapp.logic;


/**
 * Private (incomplete) implementation of CalculatorIntf.
 * 
 * @author Sven Graupner
 *
 */
class CalculatorLogic implements CalculatorIntf {
	private StringBuffer dsb = new StringBuffer();
	private final double VAT_RATE = 19.0;
	private CalculatorIntf calcGui = null;


	@Override
	public void start() { }

	@Override
	public void stop() { }

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void inject( CalculatorIntf component ) {
		calcGui = component;
	}

	@Override
	public void write( int selector, String text ) {
		if( calcGui != null ) {
			calcGui.write( selector, text );
		} else {
			//TODO: log
			System.out.println( text );
		}
	}

	@Override
	public void nextToken( Token tok ) {
		try {
			switch( tok ) {
			case K_0:	appendBuffer( "0" ); break;
			case K_1:	appendBuffer( "1" ); break;
			case K_2:	appendBuffer( "2" ); break;
			case K_3:	appendBuffer( "3" ); break;
			case K_4:	appendBuffer( "4" ); break;
			case K_5:	appendBuffer( "5" ); break;
			case K_6:	appendBuffer( "6" ); break;
			case K_7:	appendBuffer( "7" ); break;
			case K_8:	appendBuffer( "8" ); break;
			case K_9:	appendBuffer( "9" );
				break;

			case K_1000:appendBuffer( "000" );
				break;

			case K_DIV:
				throw new ArithmeticException( "ERR: div by zero" );
			case K_MUL:	appendBuffer( "*" ); break;
			case K_PLUS:appendBuffer( "+" ); break;
			case K_MIN:	appendBuffer( "-" ); break;
			case K_EQ:	appendBuffer( "=" ); break;

			case K_VAT:
				writeSideArea(
					"Brutto:  1,000.00\n" +
					VAT_RATE + "% MwSt:  159.66\n" +
					"Netto:  840.34"
				);
				break;

			case K_DOT:	appendBuffer( "." );
				break;

			case K_BACK:
				dsb.setLength( Math.max( 0, dsb.length() - 1 ) );
				break;

			case K_C:
				writeSideArea( "" );
			case K_CE:
				dsb.delete( 0,  dsb.length() );
				break;

			default:
			}
			String display = dsb.length()==0? "0" : dsb.toString();
			writeDisplay( display );

		} catch( ArithmeticException e ) {
			writeDisplay( e.getMessage() );
		}
	}


	/*
	 * Private method(s).
	 */

	private void appendBuffer( String d ) {
		if( dsb.length() <= DISPLAY_MAXDIGITS ) {
			dsb.append( d );
		}
	}

	private void writeDisplay( String text ) {
		write( 0, text );
	}

	private void writeSideArea( String text ) {
		write( 1, text );
	}

}
