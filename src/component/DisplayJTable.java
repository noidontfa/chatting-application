package component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.transfer.TooltipModel;

public class DisplayJTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public DisplayJTable() {
		this.setModel(new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {

				return false;
			}
		});
		this.setRowSelectionAllowed(false);
		this.setFillsViewportHeight(true);
		this.setShowVerticalLines(false);
		this.setShowHorizontalLines(false);
		this.setShowGrid(false);
		this.setTableHeader(null);
		this.setDefaultRenderer(Object.class, new MyRender());
		this.setBackground(Color.WHITE);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {		
				autoScroll();
			}
		});
		// table.setRowHeight(80);

		tableModel = (DefaultTableModel) this.getModel();

		tableModel.setColumnCount(1);
		
	}
	
	public void removeAllRow() {
		while(tableModel.getRowCount() > 0 ) {
			tableModel.removeRow(0);
		}
	}

	public void addRow(TooltipModel tooltipModel) {
		tableModel.addRow(new Object[] { tooltipModel });
		
	}

	public class MyRender implements TableCellRenderer {



		public MyRender() {
			setBackground(Color.WHITE);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			TooltipModel tooltipModel = (TooltipModel) value;
			
			Tooltip tooltip = new Tooltip();
			if (tooltipModel.isLeft()) {
				tooltip.displayLeft(tooltipModel);
			} else {
				tooltip.displayRight(tooltipModel);
			}
			
			table.setRowHeight(row, tooltip.getHeight() + 9);
			return tooltip;
		}
		

	}
	
	private void autoScroll() {
		this.scrollRectToVisible(this.getCellRect(this.getRowCount() - 1 , 0, true));
	}

}
