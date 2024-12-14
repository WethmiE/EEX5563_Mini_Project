import java.util.ArrayList;
import java.util.List;

class MemoryBlock {
    int size;
    int allocatedSize; // Simulates the size currently allocated in this block
    boolean isAllocated;

    MemoryBlock(int size) {
        this.size = size;
        this.allocatedSize = 0;
        this.isAllocated = false;
    }
}

public class DynamicFirstFit {
    private List<MemoryBlock> memory;

    public DynamicFirstFit() {
        memory = new ArrayList<>();
    }

    // Dynamically allocate memory blocks
    public void addMemoryBlock(int size) {
        if (size <= 0) {
            System.out.println("Invalid memory block size. Size must be greater than 0.");
            return;
        }
        memory.add(new MemoryBlock(size));
        System.out.println("Added a memory block of size " + size + " KB.");
    }

    // Allocate memory to a process
    public void allocateMemory(int processSize) {
        if (processSize <= 0) {
            System.out.println("Invalid process size. Size must be greater than 0.");
            return;
        }
        for (MemoryBlock block : memory) {
            if (!block.isAllocated && block.size >= processSize) {
                block.allocatedSize = processSize;
                block.isAllocated = true;
                System.out.println("Allocated " + processSize + " KB to a block of size " + block.size + " KB.");
                return;
            }
        }
        System.out.println("Failed to allocate " + processSize + " KB. No suitable block available.");
    }

    // Deallocate a specific block by index
    public void deallocateMemory(int blockIndex) {
        if (blockIndex < 0 || blockIndex >= memory.size()) {
            System.out.println("Invalid block index.");
            return;
        }
        MemoryBlock block = memory.get(blockIndex);
        if (block.isAllocated) {
            block.isAllocated = false;
            block.allocatedSize = 0;
            System.out.println("Deallocated memory block at index " + blockIndex + ".");
        } else {
            System.out.println("Block at index " + blockIndex + " is already free.");
        }
    }

    // Display memory status
    public void displayMemoryStatus() {
        System.out.println("Memory Status:");
        for (int i = 0; i < memory.size(); i++) {
            MemoryBlock block = memory.get(i);
            System.out.println("Block " + i + ": Size = " + block.size + " KB, " +
                    "Allocated = " + block.isAllocated + ", Allocated Size = " + block.allocatedSize + " KB.");
        }
    }

    public static void main(String[] args) {
        DynamicFirstFit memoryManager = new DynamicFirstFit();

        // Dynamically adding memory blocks
        memoryManager.addMemoryBlock(100);
        memoryManager.addMemoryBlock(500);
        memoryManager.addMemoryBlock(200);
        memoryManager.addMemoryBlock(300);
        memoryManager.addMemoryBlock(600);

        memoryManager.displayMemoryStatus();

        // Allocating memory to processes
        memoryManager.allocateMemory(150);
        memoryManager.allocateMemory(700);
        memoryManager.allocateMemory(200);

        memoryManager.displayMemoryStatus();

        // Deallocating memory
        memoryManager.deallocateMemory(1);
        memoryManager.displayMemoryStatus();
    }
}
